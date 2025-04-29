import { deleteResident, updateResident, searchResident } from '../service/api-service.js';

export async function deleteRowController(residentId) {
  try {
    const data = await deleteResident(residentId);

    if (data.message) {
      return data;
    }

    return {
      success: false,
      message: data.message || "Error occurred",
    };
  } catch (error) {
    return {
      success: false,
      message: error.message || "Client error",
    };
  }
}

export async function updateRowController(residentId, residentToBeUpdate) {
  try {
    const response = await updateResident(residentId, residentToBeUpdate);

    if(response.message) {
      return response;
    }

    console.error('Error updating resident with id: ' + residentId);
    return {
      success: false,
      message: 'Update resident with id ' + residentId + ' failed'
    };
  } catch (error) {
    console.error('Internal service error', error);
    return {
      success: false,
      message: 'Update failed. Error occurred'
    };
  }
}

export async function searchController(keyword) {
  try {
    return await searchResident(keyword);
  } catch (error) {
    console.error('Internal service error', error);
    return null;
  }
}
