export const loginStart = (userCredentials) => ({
    type: "LOGIN_START"
});

export const loginSuccess = (userId) => ({
    type:"LOGIN_SUCCESS",
    payload: userId,
});

export const loginFailure = () => ({
    type:"LOGIN_FAILURE"
});

export const logout = () => ({
    type:"LOGOUT"
});

export const UpdateStart = (userCredentials) => ({
    type: "UPDATE_START",
});

export const UpdateSuccess = (userId) => ({
    type: "UPDATE_SUCCESS",
    payload: userId,
});

export const UpdateFailure = () => ({
    type: "UPDATE_FAILURE",
});