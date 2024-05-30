import React, { useState } from 'react';
import Header from '../Header';
import { addServiceCenter } from "../utils/ApiFuntions";

function AddServiceCenter() {
  const [serviceCenterName, setServiceCenterName] = useState('');
  const [serviceCenterAddress, setServiceCenterAddress] = useState('');
  const [serviceCenterContact, setServiceCenterContact] = useState('');
  const [showSuccessMessage, setShowSuccessMessage] = useState(false);

  const handleServiceCenterSubmit = async (e) => {
    e.preventDefault();

    const serviceCenterData = {
      serviceCenterName: serviceCenterName,
      serviceCenterAddress: serviceCenterAddress,
      serviceCenterContact: serviceCenterContact,
    };

    try {
      const response = await addServiceCenter(serviceCenterData);
      if (response.success) {
        setShowSuccessMessage(true);
        // Reset form fields after 3 seconds
        setTimeout(() => {
          setShowSuccessMessage(false);
          setServiceCenterName('');
          setServiceCenterAddress('');
          setServiceCenterContact('');
        }, 3000);
      } else {
        console.error('Failed to add service center');
      }
    } catch (error) {
      console.error('Error adding service center:', error);
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
                <h2 className="text-2xl font-bold leading-tight text-black">Add Service Center</h2>
                <form onSubmit={handleServiceCenterSubmit} className="mt-8">
                  <div className="space-y-5">
                    <div>
                      <label htmlFor="serviceCenterName" className="text-base font-medium text-gray-900">Service Center Name</label>
                      <div className="mt-2">
                        <input
                          className="flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1"
                          type="text"
                          value={serviceCenterName}
                          onChange={(e) => setServiceCenterName(e.target.value)}
                          placeholder="Service Center Name"
                          id="serviceCenterName"
                        />
                      </div>
                    </div>
                    <div>
                      <label htmlFor="serviceCenterAddress" className="text-base font-medium text-gray-900">Service Center Address</label>
                      <div className="mt-2">
                        <input
                          className="flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1"
                          type="text"
                          value={serviceCenterAddress}
                          onChange={(e) => setServiceCenterAddress(e.target.value)}
                          placeholder="Service Center Address"
                          id="serviceCenterAddress"
                        />
                      </div>
                    </div>
                    <div>
                      <label htmlFor="serviceCenterContact" className="text-base font-medium text-gray-900">Contact</label>
                      <div className="mt-2">
                        <input
                          className="flex h-10 w-full rounded-md border border-gray-300 bg-transparent px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1"
                          type="text"
                          value={serviceCenterContact}
                          onChange={(e) => setServiceCenterContact(e.target.value)}
                          placeholder="Contact"
                          id="serviceCenterContact"
                        />
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
                    <p className="text-sm font-medium text-gray-900">Service center details saved!</p>
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

export default AddServiceCenter;
