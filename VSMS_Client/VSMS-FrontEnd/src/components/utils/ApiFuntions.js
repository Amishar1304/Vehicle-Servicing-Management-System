import axios from "axios"

export const api = axios.create({
	baseURL: "http://localhost:8080"
})

/* This function gets all customers from thee database */
export async function getAllCustomers() {
	try {
		const response = await api.get("/customers/getAll")
		return response.data
	} catch (error) {
		throw new Error("Error fetching Customers")
	}
}

/* This function gets a Customer by the id */
export async function getCustomerById(ID) {
	try {
		const result = await api.get(`/customers/getById/${ID}`)
		return result.data
	} catch (error) {
		throw new Error(`Error fetching room ${error.message}`)
	}
}

/* This function adds a new customer and associated vehicles to the database */
export async function addCustomerAndVehicles(customerData) {
	try {
	  const response = await api.post('/customers/save', customerData);
	  return response.data;
	} catch (error) {
	  throw error;
	}
  }

  /* This function adds a new service record for a vehicle */
export async function addServiceRecord(serviceRecordData) {
	try {
	  const response = await api.post('/serviceRecord/save', serviceRecordData);
	  return response.data;
	} catch (error) {
	  throw error;
	}
  }

/* This function gets all vehicles from the database */
export async function getAllVehicles() {
	try {
	  const response = await api.get("/vehicles/getAll");
	  return response.data;
	} catch (error) {
	  throw new Error("Error fetching Vehicles");
	}
  }

  
/* This function adds a new Technician to the database */
export async function addTechnician(technicianData) {
	try {
	  const response = await api.post('/technician/save', technicianData);
	  return response.data;
	} catch (error) {
	  throw error;
	}
  }

  /* This function gets all technicians from the database */
export async function getAllTechnician() {
  try {
      const response = await api.get("/technician/getAll");
      return response.data;
  } catch (error) {
      throw new Error("Error fetching Technicians");
  }
}

/* This function gets a Technician by ID */
export async function getTechnicianById(ID) {
  try {
      const result = await api.get(`/technician/getById/${ID}`);
      return result.data;
  } catch (error) {
      throw new Error(`Error fetching technician: ${error.message}`);
  }
}



/* This function adds a new Technician to the database */
export async function addServiceCenter(serviceCenterData) {
	try {
	  const response = await api.post('/serviceCenter/save', serviceCenterData);
	  return response.data;
	} catch (error) {
	  throw error;
	}
  }


  /* This function fetches service details from the database */
export async function serviceDetails(ID) {
	try {
	  const response = await api.get(`/serviceDetails/details/${ID}`);
	  return response.data;
	} catch (error) {
	  throw error;
	}
}

/* This function fetches vehicle assigned details from the database */


// Function to fetch all vehicle assignments
export const getAllVehicleAssignments = async () => {
  try{
  const response = await api.get("/VehicleAssigned/getAll"); // Adjust the URL as needed
  return response.data;
  } catch (error) {
      throw new Error("Error fetching Technicians");
  }
}


export const assignVehicleToTechnician = async (assignmentData) => {
  try {
    const response = await api.post("/VehicleAssigned/save", assignmentData); // Send assignmentData in the request body
    return response.data;
  } catch (error) {
    throw new Error("Error assigning vehicle to technician: " + error.message);
  }
};

/* This function deletes a deleteVehicleAssignment  by ID */
export async function deleteVehicleAssignment (assignmentId) {
  try {
      const response = await api.delete(`/VehicleAssigned/deleteById/${assignmentId}`);
      return response.data;
  } catch (error) {
      throw new Error(`Failed to delete vehicle assignment: ${error.message}`);
  }
}






export const getAllTechnicians = async () => {
  try {
    const response = await fetch("http://localhost:8080/technician/getAll");
    if (response.ok) {
      const data = await response.json();
      return data;
    } else {
      throw new Error("Failed to fetch technicians");
    }
  } catch (error) {
    console.error("Error fetching technicians:", error);
    return [];
  }
};




/* This function requests the backend to generate a PDF based on the customer ID */
export const generatePdf = async (customerId) => {
  try {
    const response = await axios.get(`http://localhost:8080/api/pdfGenerate/${customerId}`, {
      responseType: "blob", // Important
    });
    const url = window.URL.createObjectURL(new Blob([response.data]));
    const link = document.createElement("a");
    link.href = url;
    link.setAttribute("download", `customer_${customerId}.pdf`);
    document.body.appendChild(link);
    link.click();
  } catch (error) {
    throw new Error("Error generating PDF");
  }
};



/* This function deletes a customer by ID */
export async function deleteCustomerById(customerId) {
    try {
        const response = await api.delete(`/customers/deleteById/${customerId}`);
        return response.data;
    } catch (error) {
        throw new Error(`Error deleting customer: ${error.message}`);
    }
}

/* This function updates a customer by ID */
export const updateCustomerById = async (customerId, updatedCustomer) => {
  try {
    const response = await api.put(`/customers/update/${customerId}`, updatedCustomer);
    return response.data;
  } catch (error) {
    throw new Error(error.message);
  }
};


/* This function updates a service record by ID */
export const updateServiceRecordById = async (serviceRecordId, updatedServiceRecord) => {
  try {
    const response = await api.put(`/serviceRecord/update/${serviceRecordId}`, updatedServiceRecord);
    return response.data;
  } catch (error) {
    throw new Error(error.message);
  }
};

/* This function deletes a Technician by ID */
export async function deleteTechnicianById(technicianId) {
  try {
      const response = await api.delete(`/technician/deleteById/${technicianId}`);
      return response.data;
  } catch (error) {
      throw new Error(`Error deleting technician: ${error.message}`);
  }
}

/* This function updates a customer by ID */
export const updateTechnicianById = async (technicianId, updatedTechnician) => {
  try {
    const response = await api.put(`/technician/update/${technicianId}`, updatedTechnician);
    return response.data;
  } catch (error) {
    throw new Error(error.message);
  }
};


























