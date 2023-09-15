import React from 'react'
import { Container, Nav, Navbar } from 'react-bootstrap'
import '../styles/Footer.css'

const Footer = () => {
    return (
        <>
            <Navbar bg='light' className='footer'>
                <Container>
                    <Nav>
                        <Nav.Link>
                            All &copy; Rights reserved to Nexus Bank
                        </Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
        </>
    )
}

export default Footer