const reducer = (state, action) => {
    switch(action.type) {
        case "LOGIN_START":
            return {
                userId: null,
                isFetching: true,
                error: false,
            };
        case "LOGIN_SUCCESS":
            return {
                userId: action.payload,
                isFetching: false,
                error: false,
            };
        case "LOGIN_FAILURE":
            return {
                userId: null,
                isFetching: false,
                error: true,
            };
        case "UPDATE_START":
            return {
                ...state,
                isFetching:true
            };
        case "UPDATE_SUCCESS":
            return {
                userId: action.payload,
                isFetching: false,
                error: false,
            };
        case "UPDATE_FAILURE":
            return {
                userId: state.user,
                isFetching: false,
                error: true,
            };
        case "LOGOUT":
            return {
                userId: null,
                isFetching: false,
                error: false,
            };
        default:
            return state;
    }
};

export default reducer;