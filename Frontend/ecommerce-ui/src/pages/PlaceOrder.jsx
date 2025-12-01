import { useState } from "react";
import {useNavigate, useLocation} from 'react-router-dom';
import { Container, Box, TextField, Button, Typography, Paper} from "@mui/material";

export default function PlaceOrder(){
    const location = useLocation();
    const navigate = useNavigate();

    const product = location.state?.product;
    const [quantity, setQuantity] = useState(1);

    if(!product){
        return (
            <Container>
                <Typography sx={{ mt: 8}}>
                    No Product Selected!
                </Typography>
            </Container>
        );
    }
    
    const handlePlaceOrder = async() => {
        try{
            const jwt = localStorage.getItem("jwt");
            const userId = Number(localStorage.getItem("userId"));

            const res = await fetch("http://localhost:8080/orders", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${jwt}`,
                },
                body: JSON.stringify({
                    productId: product.id,
                    quantity: quantity,
                    userId: userId,
                }),
            });

            if(!res.ok) throw new Error("Failed to place order");
            console.log(res);
            alert("Order Place Successfully");
            navigate("/products");
        }catch(error){
            console.error(error);
            alert("Something went Wrong!");
        }
    }
    return (
    <Container maxWidth="sm">
      <Paper elevation={3} sx={{ p: 4, mt: 8, borderRadius: 3 }}>
        <Typography variant="h5" gutterBottom>
          Place Order
        </Typography>
        <Typography variant="h6">{product.name}</Typography>
        <Typography color="text.secondary" sx={{ mb: 2 }}>
          {product.description}
        </Typography>
        <Typography variant="subtitle1" color="primary" sx={{ mb: 2 }}>
          Price: ₹{product.price}
        </Typography>

        <Box sx={{ display: "flex", alignItems: "center", mb: 2 }}>
          <TextField
            label="Quantity"
            type="number"
            value={quantity}
            onChange={(e) => setQuantity(Number(e.target.value))}
            inputProps={{ min: 1 }}
            sx={{ mr: 2, width: 100 }}
          />
          <Typography variant="subtitle1">
            Total: ₹{quantity * product.cost}
          </Typography>
        </Box>

        <Button
          variant="contained"
          color="primary"
          fullWidth
          onClick={handlePlaceOrder}
        >
          Place Order
        </Button>
      </Paper>
    </Container>
  );
};