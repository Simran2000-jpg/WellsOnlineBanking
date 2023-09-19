import React, { useEffect, useState } from 'react'
import { Container } from 'react-bootstrap'
import { NavLink } from 'react-router-dom'
import PersonalDetailsForm from '../components/PersonalDetailsForm'
import AddressDetailsForm from '../components/AddressDetailsForm'
import OccupationDetailsForm from '../components/OccupationDetailsForm'

const OpenAccount = () => {
    const [stepNumber, setStepNumber] = useState(1);

    const getElementForCurrentStep = () => {
        switch (stepNumber) {
            case 1:
                return <PersonalDetailsForm />
            case 2:
                return <AddressDetailsForm />
            case 3:
                return <OccupationDetailsForm />

            default:
                return <></>;
        }
    }

    return (
        <div className="form-bg my-5 mx-auto">
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-md-offset-3 col-md-8 col-sm-offset-2 col-sm-12">
                        <div className="form-container">
                            <h3 className="title">Open an Account</h3>
                            <div className="progress mb-3" style={{height: '5px'}}>
                                <div className="progress-bar progress-bg" style={{ width: `${33*(stepNumber-1)}%` }}></div>
                            </div>
                            {getElementForCurrentStep()}
                            <div className="container">
                                <div className="row">
                                    <div className="col">
                                        <div className="form-horizontal mb-0">
                                            <div className="d-flex justify-content-between">
                                                {stepNumber > 1
                                                    ?
                                                    <button
                                                        className="btn btn-warning mx-0 my-0"
                                                        type="submit"
                                                        onClick={() => { setStepNumber(prevState => prevState - 1) }}
                                                    >
                                                        <i className="bi bi-caret-left-fill"></i>
                                                        Back
                                                        <span></span>
                                                    </button>
                                                    : <div></div>
                                                }
                                                <button
                                                    className="btn btn-success mx-0 my-0"
                                                    type="submit"
                                                    onClick={() => { setStepNumber(prevState => prevState + 1) }}
                                                >
                                                    {stepNumber === 3
                                                        ? "Submit"
                                                        : <>
                                                            Next
                                                            <i className="bi bi-caret-right-fill"></i>
                                                        </>}
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default OpenAccount