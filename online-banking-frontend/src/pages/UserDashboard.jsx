import React from 'react'
import { Container } from 'react-bootstrap'
import '../styles/Home.css'
import SidebarComponent from '../components/SidebarComponent'
import AccountDetails from '../components/AccountDetails'
import AccountStatement from '../components/AccountStatement'
import { useLocation } from 'react-router-dom'
import Home from './Home'
import Transaction from './Transaction'
import AddBeneficiary from './AddBeneficiary'
import ViewBeneficiary from './ViewBeneficiary'

const UserDasboard = () => {
    const location = useLocation();

    const path = location.pathname.split("/");
    console.log(path);

    return (
        <Container className='pt-2'>
            <SidebarComponent/>
            {
                (path[2] === "account-details") ? <AccountDetails/> : 
                (path[2] === "account-statement") ? <AccountStatement/> : 
                (path[2] === "funds-transfer") ? <Transaction/> :
                (path[2] === "add-beneficiary") ? <AddBeneficiary/> :
                (path[2] === "view-beneficiary") ? <ViewBeneficiary/> :
                ""
            }
        </Container>
    )
}

export default UserDasboard