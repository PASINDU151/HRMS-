import React, { useState } from 'react';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleLogin = async () => {
        try {
          const requestBody = {
            user_id: null,
            username,
            password,
            employee_id: null,
        };

        const response = await fetch('http://192.168.43.214:8080/users/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestBody),
        });

            // Inside handleLogin function in Login.js
        if (response.ok) {
            const userData = await response.json();
            localStorage.setItem('userData', JSON.stringify(userData));
            window.location.href = '/profile';
        }
        else {
                setError('Incorrect username or password');
            }
        } catch (error) {
            console.error('Error:', error);
            
            setError('An error occurred during login', error);
        }
    };

    return (
        <div>
            <h2>Login</h2>
            {error && <p>{error}</p>}
            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <button onClick={handleLogin}>Login</button>
        </div>
    );
}

export default Login;
