import { getAllResidents } from '../service/api-service.js';
import { deleteRowController, updateRowController, searchController } from '../controller/admin-dashboard-controller.js';
display();
renderUpdateModal();

async function display() {
  try {
    const residentsData = await getAllResidents();
    if (residentsData) {
      renderResidentsTable(residentsData);
    }
  } catch (error) {
    console.error('Failed to load residents:', error);
  }
}

async function deleteRow(buttonElement) {
  const residentId = buttonElement.getAttribute('data-id');
  if (!residentId) return;

  if (!confirm("Are you sure you want to delete this resident?")) return;

  try {
      const result = await deleteRowController(residentId);
      
      if (result?.success) {
          alert(result.message);
          display(); // Refresh table
      } else {
          alert(result?.message || "Deletion failed");
      }
  } catch (error) {
      alert(error.message); // Show actual error message
      console.error("Delete error:", error);
  }
}

function renderUpdateModal() {
  document.getElementById('updateModalContainer').innerHTML = `
    <div class="modal fade" id="updateResidentModal" tabindex="-1" aria-labelledby="updateModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <form id="updateResidentForm">
            <div class="modal-header">
              <h5 class="modal-title" id="updateModalLabel">Update Resident</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <!-- Input fields -->
              <input type="hidden" id="residentId">
              <div class="mb-2">
                <label>First Name</label>
                <input class="form-control" type="text" id="firstname1" required>
              </div>
              <div class="mb-2">
                <label>Last Name</label>
                <input class="form-control" type="text" id="lastname1" required>
              </div>
              <div class="mb-2">
                <label>Middle Name</label>
                <input class="form-control" type="text" id="middlename1">
              </div>
              <div class="mb-2">
                <label>Suffix</label>
                <input class="form-control" type="text" id="suffix">
              </div>
              <div class="mb-2">
                <label>Age</label>
                <input class="form-control" type="number" id="age1" required>
              </div>
              <div class="input-field">
                        <input required class="input" type="text" id="contactNumber">
                        <label class="label" for="contactNumber">Contact Number</label>
              </div>
              <div class="mb-2">
                <label>Birthday</label>
                <input class="form-control" type="date" id="birthday1" required>
              </div>
              <div class="mb-2">
                <label>Gender</label>
                <select class="form-select" id="gender1" required>
                  <option value="" disabled selected>Choose</option>
                  <option value="Male">Male</option>
                  <option value="Female">Female</option>
                  <option value="Other">Other</option>
                </select>
              </div>
              
              <!-- Address Fields -->
              <div class="mb-2">
                <label>House Number</label>
                <input class="form-control" type="text" id="houseNumber" required>
              </div>
              <div class="mb-2">
                <label>Street</label>
                <input class="form-control" type="text" id="street" required>
              </div>
              <div class="mb-2">
                <label>Subdivision</label>
                <input class="form-control" type="text" id="subdivision">
              </div>
              <div class="mb-2">
                <label>Barangay</label>
                <input class="form-control" type="text" id="barangay" required>
              </div>
              <div class="mb-2">
                <label>City/Municipality</label>
                <input class="form-control" type="text" id="cityMunicipality" required>
              </div>
              <div class="mb-2">
                <label>Province</label>
                <input class="form-control" type="text" id="province" required>
              </div>
              <div class="mb-2">
                <label>Postal Code</label>
                <input class="form-control" type="text" id="postalCode" required>
              </div>
              <div class="mb-2">
                <label>Region</label>
                <input class="form-control" type="text" id="region" required>
              </div>

              <div class="mb-2">
                <label>Status</label>
                <select class="form-select" id="status" required>
                  <option value="" disabled selected>Choose</option>
                  <option value="SINGLE">Single</option>
                  <option value="MARRIED">Married</option>
                  <option value="WIDOWED">Widowed</option>
                </select>
              </div>
              <div class="mb-2">
                <label>Purpose</label>
                <select class="form-select" id="purpose" required>
                  <option value="" disabled selected>Select Purpose</option>
                  <option value="WORK">Work</option>
                  <option value="SCHOOL">School</option>
                  <option value="MEDICAL">Medical Requirements</option>
                  <option value="BUSINESS">Business</option>
                </select>
              </div>
            </div>
            <div class="modal-footer">
              <button type="submit" class="btn btn-success">Update</button>
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  `;
}

function renderResidentsTable(residentsData) {
  const tableBody = document.querySelector('.table tbody');
  tableBody.innerHTML = residentsData.map(resident => `
    <tr>
      <td>${resident.lastName}</td>
      <td>${resident.firstName}</td>
      <td>${resident.middleName}</td>
      <td>${resident.suffix}</td>
      <td>${resident.age}</td>
      <td>${resident.contactNumber}</td>
      <td>${resident.nationalId}</td>
      <td>${resident.birthDate}</td>
      <td>${resident.gender}</td>
      <td>${resident.cityMunicipality}</td>
      <td>${resident.status}</td>
      <td>${resident.purpose}</td>
      <td>
        <button class="update-residents btn btn-primary btn-sm" data-id="${resident.id}"
          data-resident='${JSON.stringify(resident)}'>Edit</button>
        <button class="delete-residents" data-id="${resident.id}">Delete</button>
      </td>
    </tr>
  `).join('');

  // Add event listeners after rendering
  document.querySelectorAll('.delete-residents').forEach(button => {
    button.addEventListener('click', function() {
      deleteRow(this); // Now 'this' will refer to the button element
    });
  });

  // event click to update a row
  let residentId;
  document.querySelectorAll('.update-residents').forEach(button => {
    button.addEventListener('click', function () {
      const resident = JSON.parse(this.getAttribute('data-resident'));
      residentId = this.getAttribute('data-id');

      // Populate modal fields
      document.getElementById('firstname1').value = resident.firstName;
      document.getElementById('lastname1').value = resident.lastName;
      document.getElementById('middlename1').value = resident.middleName || '';
      document.getElementById('suffix').value = resident.suffix || '';
      document.getElementById('contactNumber').value = resident.contactNumber;
      document.getElementById('age1').value = resident.age;
      document.getElementById('birthday1').value = resident.birthDate;
      document.getElementById('gender1').value = resident.gender;
      document.getElementById('houseNumber').value = resident.houseNumber || '';
      document.getElementById('street').value = resident.street || '';
      document.getElementById('subdivision').value = resident.subdivision || '';
      document.getElementById('barangay').value = resident.barangay || '';
      document.getElementById('cityMunicipality').value = resident.cityMunicipality || '';
      document.getElementById('province').value = resident.province || '';
      document.getElementById('postalCode').value = resident.postalCode || '';
      document.getElementById('region').value = resident.region || '';
      document.getElementById('status').value = resident.status;
      document.getElementById('purpose').value = resident.purpose;
  
      // Show modal
      const modal = new bootstrap.Modal(document.getElementById('updateResidentModal'));
      modal.show();
    });

    document.addEventListener('submit', async function (e) {
      if (e.target.id === 'updateResidentForm') {
        e.preventDefault();
    
        const updatedData = {
          purpose: document.getElementById('purpose').value,
          firstName: document.getElementById('firstname1').value,
          lastName: document.getElementById('lastname1').value,
          middleName: document.getElementById('middlename1').value,
          suffix: document.getElementById('suffix').value,
          contactNumber: document.getElementById('contactNumber').value,
          age: parseInt(document.getElementById('age1').value),
          gender: document.getElementById('gender1').value,
          status: document.getElementById('status').value,
          houseNumber: document.getElementById('houseNumber').value,
          street: document.getElementById('street').value,
          subdivision: document.getElementById('subdivision').value,
          barangay: document.getElementById('barangay').value,
          cityMunicipality: document.getElementById('cityMunicipality').value,
          province: document.getElementById('province').value,
          postalCode: document.getElementById('postalCode').value,
          region: document.getElementById('region').value,
          birthDate: document.getElementById('birthday1').value,
        };
    
        try {
          const result = await updateRowController(residentId, updatedData);
          alert(result.message);
          if (result.success !== false) {
            bootstrap.Modal.getInstance(document.getElementById('updateResidentModal')).hide();
            display(); // refresh table
          }
        } catch (err) {
          alert("Failed to update resident: " + err.message);
        }
      }
    });
  });
  
}

const searchBar = document.getElementById('search-input-js');
searchBar.addEventListener('input', debounce(async (e) => {
  const keyword = e.target.value.trim(); // search bar input
  if(keyword.length === 0) {
    display();
    return;
  }

  try {
    const searchData = await searchController(keyword);

    if(searchData.error) {
      console.error('Error fetching data: ', searchData.error);
      return;
    }

    // render table
    renderResidentsTable(searchData);
  } catch (error) {
    console.error('Internal server error: ' + error);
    return;
  }
}, 300));


// function that prevents execessive api calls
function debounce(func, delay) {
  let timer;

  return(...args) => {
    clearTimeout(timer);
    timer = setTimeout(() => func(...args), delay);
  };
}