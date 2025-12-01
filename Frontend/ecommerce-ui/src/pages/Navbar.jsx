import React from "react";
import {useNavigate, Link} from 'react-router-dom';
import { AppBar, Toolbar, Box, Button, Typography, Paper} from "@mui/material";

export default function Navbar(){
    const navigate = useNavigate();
    const jwt = localStorage.getItem("jwt");

    const handleLogout = () => {
        localStorage.removeItem("jwt");
        localStorage.removeItem("userId");
        navigate("/");
    };

    return (
    <AppBar position="fixed" sx={{ top: 0, left: 0, right: 0, width: "100%" }}>
      <Toolbar>
        <Typography variant="h6" sx={{ flexGrow: 1 }}>
          My E-Commerce
        </Typography>
        <Box sx={{ display: "flex", gap: 2 }}>
          <Button color="inherit" component={Link} to="/products">
            Products
          </Button>
          {!jwt && (
            <>
              <Button color="inherit" component={Link} to="/">
                Login
              </Button>
              <Button color="inherit" component={Link} to="/signup">
                Signup
              </Button>
            </>
          )}
          {jwt && (
            <Button color="inherit" onClick={handleLogout}>
              Logout
            </Button>
          )}
        </Box>
      </Toolbar>
    </AppBar>
  );

}