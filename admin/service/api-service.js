// Custom fetch wrapper to handle authentication
const BASE_URL = "http://localhost:8081";

async function authenticatedFetch(endpoint, options = {}) {
    const response = await fetch(`${BASE_URL}${endpoint}`, {
        ...options,
        credentials: 'include', // MUST include credentials
        headers: {
            'Content-Type': 'application/json',
            ...options.headers
        }
    });

    if (response.status === 401) {
        // Trigger re-authentication
        window.location.href = `${BASE_URL}/oauth2/authorization/google`;
        throw new Error("Authentication required");
    }

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    return response;
}

export async function getAllResidents() {
  try {
      const response = await authenticatedFetch('/api/admin/private/get-all-residents');
      
      return await response.json();
  } catch (error) {
      console.error("API Request Error:", error);
      throw error;
  }
} 

export async function deleteResident(id) {
    try {
        const response = await authenticatedFetch(`/api/admin/private/delete-resident/${id}`, {
            method: 'DELETE',
        });

        const data = await response.json(); // Always parse JSON
        
        if (!response.ok) {
            // Handle specific error cases
            if (response.status === 409) { // Conflict (constraint violation)
                throw new Error(data.message || "Resident has associated documents");
            }
            throw new Error(data.message || `HTTP error! status: ${response.status}`);
        }

        return data;
    } catch (error) {
        console.error("Delete Resident API Error:", error);
        throw error; // Re-throw for controller to handle
    }
}

export async function updateResident(id, updateResidentData) {
    try {
        const response = await authenticatedFetch(`/api/admin/private/update-resident/${id}`, {
            method: 'PUT',
            body: JSON.stringify(updateResidentData) // Send the data as JSON in the request body
        });

        if (response.ok) {
            const data = await response.json();
            console.log("Resident updated:", data);
            return data;
        } else {
            console.error("Failed to update resident:", response.status);
            throw new Error("Failed to update resident");
        }
    } catch (error) {
        console.error("API Request Error:", error);
        throw error;
    }
}

export async function searchResident(keyword) {
    try {
        const endpoint = `/api/admin/private/search-resident?keyword=${encodeURIComponent(keyword)}`;

        const response = await authenticatedFetch(endpoint);
        if (response.status === 204) {
            return []; // No content, return empty list
        }

        return await response.json();
    } catch (error) {
        console.error("Search Resident API Error:", error);
        throw error;
    }
}
