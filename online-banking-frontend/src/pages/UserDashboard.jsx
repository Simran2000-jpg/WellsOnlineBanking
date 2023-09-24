import React from 'react'
import { Container } from 'react-bootstrap'
import '../styles/Home.css'
import SidebarComponent from '../components/SidebarComponent'
import AccountDetails from '../components/AccountDetails'
import AccountStatement from '../components/AccountStatement'
import { useLocation } from 'react-router-dom'
import Home from './Home'
import Transaction from './Transaction'

const UserDasboard = () => {
    const location = useLocation();

    const path = location.pathname.split("/");

    return (
        <Container className='pt-2'>
            <SidebarComponent/>
            <h1 className='display-6'>User Dashboard</h1>
            {
                (path[2] === "account-details") ? <AccountDetails/> : (path[2] === "account-statement") ? <AccountStatement/> : (path[2] === "transaction") ? <Transaction/> : <Home/>
            }
        </Container>
    )
}

export default UserDasboard