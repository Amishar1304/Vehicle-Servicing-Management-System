import React from "react";
import Header from "./Header";
import Footer from "./Footer";
import { Link } from "react-router-dom";

function CustomerPage() {
    return(
        <>
        <Header/>
        <div>
       
        <section className="flex-grow p-2 min-h-screen flex flex-col">
                <div className="flex items-center justify-center bg-white px-4 py-10 sm:px-6 sm:py-20 lg:px-8">
                    <div className="flex flex-col sm:flex-row">
                        <Link to="/customer/check-service-details">
                            <button className="mr-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                                Check Your Vehicle Service Record
                            </button>
                        </Link>
                        <Link to="/customer/raise-query">
                            <button className="mr-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                                Raise Query
                            </button>
                        </Link>
                        <Link to="/customer/give-feedback">
                            <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                                Give Feedback
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

export default CustomerPage