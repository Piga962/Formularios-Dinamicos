import React, { createContext, useState, useContext, useEffect } from 'react';

// Create Authentication Context
const AuthContext = createContext();

// Custom hook to use the auth context
export const useAuth = () => useContext(AuthContext);

// Auth Provider Component
export const AuthProvider = ({ children }) => {
    const [currentUser, setCurrentUser] = useState(null);
    const [loading, setLoading] = useState(true);
    
    // Simulate login in a real app (replace with actual authentication logic)
    const login = (userRole) => {
        // In a real app, this would verify credentials with a backend
        // For this demo, we just set the user role directly
        const user = {
            id: `user-${Date.now()}`,
            role: userRole,
            name: userRole === 'admin' ? 'Admin User' : 'Guest User'
        };
        
        // Save to localStorage for persistence
        localStorage.setItem('currentUser', JSON.stringify(user));
        setCurrentUser(user);
        return user;
    };
    
    // Logout function
    const logout = () => {
        localStorage.removeItem('currentUser');
        setCurrentUser(null);
    };
    
    // Check if user is already logged in on page load
    useEffect(() => {
        const storedUser = localStorage.getItem('currentUser');
        if (storedUser) {
            setCurrentUser(JSON.parse(storedUser));
        }
        setLoading(false);
    }, []);
    
    // Context value
    const value = {
        currentUser,
        login,
        logout,
        isAdmin: currentUser?.role === 'admin',
        isGuest: currentUser?.role === 'guest'
    };
    
    return (
        <AuthContext.Provider value={value}>
            {!loading && children}
        </AuthContext.Provider>
    );
};

export default AuthContext;
