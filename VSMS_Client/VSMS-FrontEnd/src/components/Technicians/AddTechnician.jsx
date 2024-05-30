import React, { useState } from 'react';
import Header from '../Header';
import { addTechnician } from "../utils/ApiFuntions";

function AddTechnician() {
  const [name, setName] = useState('');
  const [contact, setContact] = useState('');
  const [shiftStatus, setShiftStatus] = useState('FREE'); // Default to FREE
  const [errors, setErrors] = useState({});
  const [showSuccessMessage, setShowSuccessMessage] = useState(false);

  const handleTechnicianSubmit = async (e) => {
    e.preventDefault();

    setShowSuccessMessage(true); // Show success message immediately

    // Validation checks
    const newErrors = {};
    if (!name.trim()) {
      newErrors.name = 'Name is required';
    }
    if (!contact.trim()) {
      newErrors.contact = 'Contact number is required';
    } else if (!/^\d{10}$/.test(contact)) {
      newErrors.contact = 'Invalid contact number';
    }

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      return;
    }

    setErrors({}); // Clear errors when there are no validation errors

    const technicianData = {
      name: name,
      contact: contact,
      shiftStatus: shiftStatus,
    };

    try {
      const response = await addTechnician(technicianData);
      if (!response.success) {
        // Show success message
        setShowSuccessMessage(true);
        // Reset form fields after 3 seconds
        setTimeout(() => {
          setShowSuccessMessage(false);
          setName('');
          setContact('');
          setShiftStatus('FREE');
        }, 3000);
      } else {
        // Handle error, maybe show an error message
        if (response.errors) {
          // Set errors received from backend
          setErrors(response.errors);
        } else {
          console.error('Failed to add technician');
        }
      }
    } catch (error) {
      console.error('Error adding technician:', error);
    }
  };

  return (
    <>
      <Header />
      <div>
        <section className="rounded-md bg-black/80 p-2">
          <div className="flex items-center justify-center bg-white px-4 py-10 sm:px-6 sm:py-20 lg:px-8">
            <div className="grid grid-cols-2 gap-8 xl:mx-auto xl:w-full xl:max-w-2xl 2xl:max-w-3xl">
              <div>
                <h2 className="text-2xl font-bold leading-tight text-black">Add Technician</h2>
                <form onSubmit={handleTechnicianSubmit} className="mt-8">
                  <div className="space-y-5">
                    <div>
                      <label htmlFor="name" className="text-base font-medium text-gray-900">Name</label>
                      <div className="mt-2">
                        <input
                          className={`flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1 ${errors.name ? 'border-red-500' : ''}`}
                          type="text"
                          value={name}
                          onChange={(e) => setName(e.target.value)}
                          placeholder="Name"
                          id="name"
                        />
                        {errors.name && <p className="text-red-500 text-xs mt-1">{errors.name}</p>}
                      </div>
                    </div>
                    <div>
                      <label htmlFor="contact" className="text-base font-medium text-gray-900">Contact</label>
                      <div className="mt-2">
                        <input
                          className={`flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1 ${errors.contact ? 'border-red-500' : ''}`}
                          type="tel"
                          value={contact}
                          onChange={(e) => setContact(e.target.value)}
                          placeholder="Contact"
                          id="contact"
                        />
                        {errors.contact && <p className="text-red-500 text-xs mt-1">{errors.contact}</p>}
                      </div>
                    </div>
                    <div>
                      <label htmlFor="shiftStatus" className="text-base font-medium text-gray-900">Shift Status</label>
                      <div className="mt-2">
                        <select
                          id="shiftStatus"
                          className={`flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1`}
                          value={shiftStatus}
                          onChange={(e) => setShiftStatus(e.target.value)}
                        >
                          <option value="FREE">Free</option>
                          <option value="BUSY">Busy</option>
                        </select>
                      </div>
                    </div>
                    <div>
                      <button
                        type="submit"
                        className="inline-flex w-full items-center justify-center rounded-md bg-black px-3.5 py-2.5 font-semibold leading-7 text-white hover:bg-black/80"
                      >
                        Save
                      </button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </section>
      </div>
      {/* Success message */}
      {showSuccessMessage && (
        <div className="fixed inset-0 flex items-end justify-center px-4 py-6 pointer-events-none sm:p-6 sm:items-start sm:justify-end">
          <div className="max-w-sm w-full bg-white shadow-lg rounded-lg pointer-events-auto">
            <div className="rounded-lg shadow-xs overflow-hidden">
              <div className="p-4">
                <div className="flex items-center">
                  <div className="ml-3 w-0 flex-1 pt-0.5">
                    <p className="text-sm font-medium text-gray-900">Technician details saved!</p>
                  </div>
                  <div className="ml-4 flex-shrink-0 flex">
                    <button
                      onClick={() => setShowSuccessMessage(false)}
                      className="inline-flex text-gray-400 focus:outline-none focus:text-gray-500 transition ease-in-out duration-150"
                    >
                      <svg className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                        <path fillRule="evenodd" d="M6.293 6.293a1 1 0 011.414 0L10 8.586l2.293-2.293a1 1 0 111.414 1.414L11.414 10l2.293 2.293a1 1 0 11-1.414 1.414L10 11.414l-2.293 2.293a1 1 0 01-1.414-1.414L8.586 10 6.293 7.707a1 1 0 010-1.414z" clipRule="evenodd" />
                      </svg>
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      )}
    </>
  );
}

export default AddTechnician;
