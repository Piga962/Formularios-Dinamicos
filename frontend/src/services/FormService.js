import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

// Service to communicate with the backend API
class FormService {
    /**
     * Fetch form configuration based on user role
     * @param {string} userRole - The role of the user (admin or guest)
     * @returns {Promise} - Promise with form configuration
     */
    async getFormByUserRole(userRole) {
        try {
            const response = await axios.get(`${API_URL}/forms/${userRole}`);
            return response.data;
        } catch (error) {
            console.error('Error fetching form:', error);
            throw error;
        }
    }
}

export default new FormService();
