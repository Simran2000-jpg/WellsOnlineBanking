import {createContext, useEffect, useReducer} from "react";
import reducer from "./Reducer";

const initialState = {
    userId: JSON.parse(localStorage.getItem("userId")) || null,
    isFetching: false,
    error: false,
};

export const Context = createContext(initialState);

export const ContextProvider = ({children}) => {
    const [state, dispatch] = useReducer(reducer, initialState);

    useEffect(() => {
        localStorage.setItem("userId", JSON.stringify(state.userId));
    }, [state.userId]);

    return (
        <Context.Provider 
            value={{
                userId: state.userId,
                isFetching: state.isFetching,
                error: state.error,
                dispatch,
            }}
        >
            {children}
        </Context.Provider>
    )
}