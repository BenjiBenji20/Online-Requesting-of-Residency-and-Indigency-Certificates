let requestType = null;

document.addEventListener("DOMContentLoaded", () => {
    const reqTypeButtonElem = document.querySelectorAll(".btn");
    const formElement = document.querySelector('#form1');

    reqTypeButtonElem.forEach((element) => {
        element.addEventListener("click", (e) => {
            requestType = e.target.textContent.trim().toLowerCase();
        });
    });

    formElement.addEventListener('submit', async (e) => {
        e.preventDefault();
    
        const residentData = {
            nationalId: Number(document.querySelector("#national-id").value.trim()),
            firstName: document.querySelector("#firstname1").value.trim(),
            lastName: document.querySelector("#lastname1").value.trim(),
            middleName: document.querySelector("#middlename1").value.trim() || null,
            suffix: document.querySelector("#suffix").value.trim() || null,
            age: Number(document.querySelector("#age1").value.trim()),
            gender: document.querySelector("#gender1").value,
            status: document.querySelector("#status").value,
            
            // Address fields
            houseNumber: document.querySelector("#houseNumber").value.trim() || null,
            street: document.querySelector("#street").value.trim() || null,
            subdivision: document.querySelector("#subdivision").value.trim() || null,  // Can be null
            barangay: document.querySelector("#barangay").value.trim() || null,
            cityMunicipality: document.querySelector("#cityMunicipality").value.trim() || null,
            province: document.querySelector("#province").value.trim() || null,
            postalCode: document.querySelector("#postalCode").value.trim() || null,
            region: document.querySelector("#region").value.trim() || null,
    
            // Additional fields
            birthDate: document.querySelector("#birthday1").value,
            purpose: document.querySelector("#purpose").value.trim(),
            contactNumber: document.querySelector("#contactNumber").value.trim()
        };
    
        // Sending data to the backend for processing
        residentRegistration(requestType, residentData);
    });
    
});


// Function to fetch api annd send data
async function residentRegistration(requestType, residentData) {
    try {
      // let response, data;
  
      // if (requestType === 'indigency' || requestType === 'residency') {
      //   response = await fetch(
      //     `http://localhost:8081/api/residents/public/register-and-request/${requestType}`,
      //     {
      //       method: "POST",
      //       headers: {
      //         "Content-Type": "application/json",
      //       },
      //       body: JSON.stringify(residentData),
      //     }
      //   );
  
      //   if (!response.ok) {
      //     const errorData = await response.json();
      //     if (errorData && typeof errorData === 'object') {
      //       displayFieldErrors(errorData); 
      //     }
      //     console.error("Error sending data");
      //     return;
      //   }
  
      //   // Handle PDF response
      //   const blob = await response.blob();
      //   const url = window.URL.createObjectURL(blob);
      //   const a = document.createElement("a");
      //   a.href = url;
      //   a.download = `${requestType}-request-info.pdf`;
      //   document.body.appendChild(a);
      //   a.click();
      //   document.body.removeChild(a);
      //   window.URL.revokeObjectURL(url);
  
      // } else {
      const otherDocument = requestType.toUpperCase();
  
      const response = await fetch(
          `http://localhost:8081/api/documents/public/request/${otherDocument}`,
          {
              method: "POST",
              headers: {
                  "Content-Type": "application/json",
              },
              body: JSON.stringify(residentData),
          }
      );
  
      const data = await response.json();
  
      if (!response.ok) {
          if (data.error) {
              alert(data.error);
          } else if (Object.keys(data).length > 0) {
                  displayFieldErrors(data);
                  clearFieldErrors()
          } else {
              alert("Unknown error occurred.");
          }
          console.error("Error sending data:", data);
          return;
      }
      alert(data.message + 
        '\nRequest Type: ' + data.request +
        '\nDue date: ' + data.time);
    // }
    } catch (error) {
      console.error("Fetch error:", error);
    }
  }
  

function displayFieldErrors(errors) {
    Object.entries(errors).forEach(([field, message]) => {
        const errorElement = document.querySelector(`#${field}Error`);
        if (errorElement) {
            errorElement.textContent = message;
            errorElement.style.display = 'inline-block';
            // Trigger fade-in
            setTimeout(() => errorElement.classList.add('show'), 10);
        }
    });
}

function clearFieldErrors() {
    const errorElements = document.querySelectorAll('.field-error');
    errorElements.forEach(el => {
        el.classList.remove('show');
        setTimeout(() => {
            el.textContent = '';
            el.style.display = 'none';
        }, 2000); 
    });
}