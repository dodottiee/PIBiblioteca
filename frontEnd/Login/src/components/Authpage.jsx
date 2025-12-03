import React, { useState } from 'react';
import LoginForm from './LoginForm';
import '../styles/main.css';

// URL do Backend
const API_URL = 'http://localhost:8080';

const AuthPage = () => {
    const [isLogin, setIsLogin] = useState(true);
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        confirmPassword: '',
    });
    const [error, setError] = useState(''); // Estado para mensagens de erro

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(''); // Limpa erros antigos

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
                
                // --- CORREÇÃO APLICADA AQUI ---
                
                // 1. Salva o Token recebido da API para uso futuro (ex: autenticação em outras chamadas)
                localStorage.setItem('token', data.token);
                
                // 2. Salva o nome do usuário digitado no formulário para ser exibido no Perfil
                localStorage.setItem('username', formData.username);
                
                // 3. Redireciona para o projeto "Inicial"
                // Ajuste a porta '5173' se o seu projeto 'frontEnd/Inicial' estiver rodando em outra.
                window.location.href = `http://localhost:5174/?username=${formData.username}`; 

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
                     const erroTxt = await response.text();
                    throw new Error(erroTxt || 'Erro ao cadastrar usuário');
                }

                console.log('Cadastro bem-sucedido');
                alert('Cadastro realizado com sucesso! Faça o login.');
                // Muda para a tela de login após o cadastro
                setIsLogin(true); 
                // Limpa os campos de senha
                setFormData(prev => ({ ...prev, password: '', confirmPassword: '' }));

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

                {/* Exibe a mensagem de erro se houver */}
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