import React, { useState } from 'react';
import LoginForm from './LoginForm';
import '../styles/main.css';

// --- MUDANÇA AQUI ---
const API_URL = 'http://localhost:8080'; // URL do Backend

const AuthPage = () => {
    const [isLogin, setIsLogin] = useState(true);
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        confirmPassword: '',
    });
    // --- MUDANÇA AQUI ---
    const [error, setError] = useState(''); // Estado para mensagens de erro

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(''); // Limpa erros antigos

        // --- MUDANÇA AQUI ---
        if (isLogin) {
            // --- LÓGICA DE LOGIN ---
            try {
                // O backend (Usuario.java) espera 'login' e 'senha'
                const response = await fetch(`${API_URL}/auth/login`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        login: formData.username,
                        senha: formData.password
                    })
                });

                if (!response.ok) {
                    throw new Error('Usuário ou senha inválidos');
                }

                const data = await response.json(); // Ex: { token: "..." }
                console.log('Login bem-sucedido:', data);
                
                // AQUI VOCÊ DEVE SALVAR O TOKEN
                // ex: localStorage.setItem('token', data.token)
                
                // E REDIRECIONAR PARA A PÁGINA INICIAL
                // No projeto, a página inicial está em /frontEnd/Inicial,
                // então você precisaria configurar um roteador (como React Router)
                // ou simplesmente redirecionar:
                // window.location.href = 'http://localhost:5173/'; // (ou a porta do 'Inicial')
                alert('Login bem-sucedido! (Redirecionamento não implementado)');

            } catch (err) {
                setError(err.message);
            }

        } else {
            // --- LÓGICA DE CADASTRO ---
            if (formData.password !== formData.confirmPassword) {
                setError('As senhas não coincidem!');
                return;
            }

            try {
                const response = await fetch(`${API_URL}/auth/register`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        login: formData.username,
                        senha: formData.password
                    })
                });

                if (!response.ok) {
                     const erroTxt = await response.text(); // Tenta ler o erro
                    throw new Error(erroTxt || 'Erro ao cadastrar usuário');
                }

                console.log('Cadastro bem-sucedido');
                alert('Cadastro realizado com sucesso! Faça o login.');
                // Muda para a tela de login após o cadastro
                setIsLogin(true); 

            } catch (err) {
                setError(err.message);
            }
        }
    };

    const handleFormToggle = (e) => {
        e.preventDefault();
        setIsLogin(!isLogin);
        setError(''); // Limpa erros ao trocar
        setFormData({ username: '', password: '', confirmPassword: '' });
    };

    return (
        <div className="login-container">
            <div className="login-wrapper">
                <h2 className="welcome-title">
                    Bem-vindo ao bookscape!
                </h2>
                
                <p className="welcome-subtitle">
                    {isLogin ? 'Faça seu login' : 'Faça seu cadastro'}
                </p>

                {/* --- MUDANÇA AQUI --- */}
                {/* Exibe a mensagem de erro */}
                {error && <p style={{ color: '#ffcdd2', paddingBottom: '10px' }}>{error}</p>}

                <LoginForm 
                    formData={formData}
                    setFormData={setFormData}
                    handleSubmit={handleSubmit}
                    isLogin={isLogin}
                    handleFormToggle={handleFormToggle}
                />
            </div>
        </div>
    );
};

export default AuthPage;