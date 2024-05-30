import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './index.css';
import LandingPage from './components/LandingPage';
import AdminPage from './components/AdminPage';
import AddCustomer from './components/AdminFuntions/AddCustomer';
import AddServiceRecord from './components/AdminFuntions/AddServiceRecord';
import GetCustomers from './components/AdminFuntions/GetCustomers';
import CustomerPage from './components/CustomerPage';
import CheckServiceRecord from './components/customers/CheckServiceRecord';
import RaiseQuery from './components/customers/RaiseQuery';
import GiveFeedback from './components/customers/GiveFeedback';
import TechnicianPage from './components/TechnicianPage';
import AddTechnician from './components/Technicians/AddTechnician';
import TechnicianDetails from './components/Technicians/TechnicianDetails';
import VehicleAssignmentPage from './components/Technicians/VehicleAssignmentPage';
import VehicleAssignmentForm from './components/Technicians/VehicleAssignmentForm';
//import VehicleAssignment from './components/Technicians/VehicleAssignmentPage';
import ServicePage from './components/ServicePage'; 
import AddServiceCenter from './components/Services/AddServiceCenter';
import ServiceDetails from './components/Services/ServiceDetails';







ReactDOM.createRoot(document.getElementById('root')).render(
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<LandingPage />} />
            <Route path="/admin" element={<AdminPage />} />
            <Route path="/admin/add-customer" element={<AddCustomer />} />
            <Route path="/admin/add-service-record" element={<AddServiceRecord />} />
            <Route path="/admin/get-customers" element={<GetCustomers />} />
            <Route path="/customer" element={<CustomerPage />} />
            <Route path="/customer/check-service-details" element={<CheckServiceRecord />} />
            <Route path="/customer/raise-query" element={<RaiseQuery />} />
            <Route path="/customer/give-feedback" element={<GiveFeedback />} />
            <Route path="/technician" element={<TechnicianPage />} />
            <Route path="/technician/add-technician" element={<AddTechnician />} />
            <Route path="/technician/technician-details" element={<TechnicianDetails />} />
            <Route path="/technician/vehicle-assignments" element={<VehicleAssignmentPage />} />
            <Route path="/technician/vehicle-assignments-form" element={<VehicleAssignmentForm />} />

           { /* <Route path="/technician/VehicleAssigned" element={<VehicleAssignment />} /> */ }
            <Route path="/service" element={<ServicePage />} />
            <Route path="/service/add-serviceCenter" element={<AddServiceCenter />} />
            <Route path="/service/service-details" element={<ServiceDetails />} /> 

           
            
        </Routes>
    </BrowserRouter>
);
