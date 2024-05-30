import React from "react";
import { Link } from 'react-router-dom';
import Header from "./Header";
import Footer from "./Footer";

function TechnicianPage() {

    return (
        <>
            <Header/>
            <div>
                <section className="flex-grow p-2 min-h-screen flex flex-col">
                    <div className="flex items-center justify-center bg-white px-4 py-10 sm:px-6 sm:py-20 lg:px-8">
                        <div className="flex flex-col sm:flex-row">
                            <Link to="/technician/add-technician">
                                <button className="mr-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                                    Add Technician
                                </button>
                            </Link>
                            <Link to="/technician/technician-details">
                                <button className="mr-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                                    Technician Details
                                </button>
                            </Link>
                            <Link to="/technician/vehicle-assignments-form">
                                <button className="mr-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                                    Vehicle Assigned Form
                                </button>
                            </Link>
                            <Link to="/technician/vehicle-assignments">
                                <button className="mr-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                                   Check Vehicle Assigned to Technician
                                </button>
                            </Link>
                            
                        </div>
                    </div>
                </section>
            </div>
            <Footer/>
        </>
    )
}

export default TechnicianPage;
