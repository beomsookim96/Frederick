// src/api/auth.js
import apiClient from './axios';

export const login = async (id, password) => {
    return apiClient.post('/login', { id : id, password : password });
};

export const logout = async () => {
    return apiClient.post('/logout');
};
