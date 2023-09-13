import React from 'react'
import { Container, Nav, Navbar } from 'react-bootstrap'

const Footer = () => {
    return (
        <>
            <Navbar bg='dark' data-bs-theme="dark">
                <Container>
                    <Nav>
                        <Nav.Link disabled>
                            All &copy; Rights reserved to Nexus Bank
                        </Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
        </>
    )
}

export default Footer