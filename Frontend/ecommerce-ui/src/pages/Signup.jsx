import { useState } from "react";
import {useNavigate} from 'react-router-dom';
import { Container, Box, TextField, Button, Typography, Paper} from "@mui/material";

export default function Signup(){

    const [formData, setFormData] = useState({
        name: "",
        email: "",
        password: "",
        confirmPassword: ""
    });

    const handleChange = (e) =>{
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };



    const handleSubmit = async(e) => {
        e.preventDefault();

        if(formData.password != formData.confirmPassword){
            alert("Passwords do not match!");
        }

        try{
            const response = await fetch("http://localhost:8080/users/signup", {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({
                    name: formData.name,
                    username: formData.email,
                    password: formData.password,
                }),
            });
            if(response.ok){
                alert("Signup successful");
            } else{
                alert("Signup failed!");
            }
        }catch(error){
            console.log("Error:", error);
            alert("Something went wrong!");
        }
    };
    return (
    <Container maxWidth="sm">
      <Paper elevation={3} sx={{ p: 4, mt: 8, borderRadius: 3 }}>
        <Typography variant="h5" gutterBottom>
          Signup
        </Typography>
        <Box component="form" onSubmit={handleSubmit}>
          <TextField
            fullWidth
            margin="normal"
            label="Name"  // 👀 shows "Username"
            name="name" 
            type="text"    // 👀 but internally it's stored as email
            value={formData.name}
            onChange={handleChange}
            required
          />
          <TextField
            fullWidth
            margin="normal"
            label="Username"  // 👀 shows "Username"
            name="email"     // 👀 but internally it's stored as email
            type="email"
            value={formData.email}
            onChange={handleChange}
            required
          />
          <TextField
            fullWidth
            margin="normal"
            label="Password"
            name="password"
            type="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
          <TextField
            fullWidth
            margin="normal"
            label="Confirm Password"
            name="confirmPassword"
            type="password"
            value={formData.confirmPassword}
            onChange={handleChange}
            required
          />
          <Button
            fullWidth
            variant="contained"
            color="primary"
            type="submit"
            sx={{ mt: 2 }}
          >
            Signup
          </Button>
        </Box>
      </Paper>
    </Container>
  );
};
