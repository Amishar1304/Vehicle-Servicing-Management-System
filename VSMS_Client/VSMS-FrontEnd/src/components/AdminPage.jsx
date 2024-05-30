import React from "react";
import { Link } from 'react-router-dom';
import Header from "./Header";
import Footer from "./Footer";



function AdminPage() {

   
    return(
        <>
        <Header/>
        <div>
       
        <section className="flex-grow p-2 min-h-screen flex flex-col">
                <div className="flex items-center justify-center bg-white px-4 py-10 sm:px-6 sm:py-20 lg:px-8">
                    <div className="flex flex-col sm:flex-row">
                        <Link to="/admin/add-customer">
                            <button className="mr-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                                Add Customer
                            </button>
                        </Link>
                        <Link to="/admin/add-service-record">
                            <button className="mr-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                                Add Service Record
                            </button>
                        </Link>
                        <Link to="/admin/get-customers">
                            <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                                Get Customers
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

export default AdminPage