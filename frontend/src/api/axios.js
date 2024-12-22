// src/api/axios.js
import axios from 'axios';

const apiClient = axios.create({
    baseURL: 'http://localhost:8080', // 기본 API URL
    headers: {
        'Content-Type': 'application/json',
    },
});

// 요청 인터셉터
apiClient.interceptors.request.use(
    (config) => {
        // 예: 인증 토큰 추가
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// 응답 인터셉터
apiClient.interceptors.response.use(
    (response) => response,
    (error) => {
        // 예: 인증 오류 처리
        if (error.response && error.response.status === 401) {
            // 로그아웃 처리 또는 로그인 페이지로 리다이렉트
        }
        return Promise.reject(error);
    }
);

export default apiClient;
