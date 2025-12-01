import { useState } from "react";
import {useNavigate} from 'react-router-dom';
import { Container, Box, TextField, Button, Typography, Paper} from "@mui/material";


export default function Login(){
    const [username, setUsername] = useState("");
    const [password,  setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async(e) =>{
        e.preventDefault();

        try{
            const response = await fetch("http://localhost:8080/users/login",{
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({username, password}),
            });

            if(!response.ok){
                throw new Error("Login Failed");
            }

            const data = await response.json();
            localStorage.setItem("jwt", data.token);
            localStorage.setItem("userId", data.userId);

            navigate("/products");
        } catch(err){
            alert(err.message);
        }
    };

    return (
        <Container>
            <Paper elevation={3} sx={{p:4, mt: 10, borderRadius: 3}}>
                <Typography variant="h5" align="centre" gutterBottom>
                    Login
                </Typography>
                <Box component="form" onSubmit={handleLogin}>
                    <TextField
                        fullWidth
                        label="Username"
                        variant="outlined"
                        margin="normal"
                        value={username}
                        type="email"
                        onChange={(e) => setUsername(e.target.value)}
                    >  
                    </TextField>
                    <TextField
                        fullWidth
                        type="password"
                        label="password"
                        variant="outlined"
                        margin="normal"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}                    
                    ></TextField>
                    <Button type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        sx={{ mt: 2 }}>
                            Login
                    </Button>
                </Box>
            </Paper>
        </Container>
    );
};
