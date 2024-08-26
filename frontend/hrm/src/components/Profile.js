import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

function Profile() {
    const { userId } = useParams();
    const [user, setUser] = useState(null);

    useEffect(() => {
        const fetchUser = async () => {
            const userData = JSON.parse(localStorage.getItem('userData'));
            setUser(userData);
        };
        fetchUser();
    }, [userId]);

    return (
        <div>
            <h2>Profile</h2>
            {user && (
                <div>
                    <p>User ID: {user.user_id}</p>
                    <p>Username: {user.username}</p>
                </div>
            )}
        </div>
    );
}

export default Profile;
