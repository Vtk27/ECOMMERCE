import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import { Container, Card, Grid, CardContent, CardActions, Box, TextField, Button, Typography, Paper } from "@mui/material";


export default function ProductList() {
    const [products, setProducts] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                const jwt = localStorage.getItem("jwt");
                const res = await fetch("http://localhost:8080/products", {
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: `Bearer ${jwt}`,
                    },
                });

                if (!res.ok) throw new Error("Failed to fetch products");
                const data = await res.json();
                setProducts(data);
            } catch (error) {
                console.error(error);
            }
        };
        fetchProducts();
    }, []);
    return (
        <Container sx={{ mt: 4 }}>
            <Typography variant="h4" gutterBottom align="center">
                Product List
            </Typography>

            <Grid
                container
                spacing={3}
                sx={{ padding: 2 }}
            >
                {products.map((product) => (
                    <Grid
                        item
                        key={product.id}
                        sx={{
                            flexBasis: "25%",   // each card = 25% width
                            maxWidth: "25%",
                            flexGrow: 0,
                        }}
                    >
                        <Card
                            sx={{
                                height: 400,
                                display: "flex",
                                flexDirection: "column",
                                justifyContent: "space-between",
                            }}
                        >
                            {/* Image */}
                            <Box sx={{ height: 160, display: "flex", alignItems: "center", justifyContent: "center" }}>
                                <img
                                    src={`/images/${product.imagesrc}`}
                                    alt={product.name}
                                    style={{ maxWidth: "100%", maxHeight: "100%", objectFit: "contain" }}
                                />
                            </Box>

                            {/* Content */}
                            <CardContent sx={{ flexGrow: 1, textAlign: "center" }}>
                                <Typography variant="h6" noWrap>{product.name}</Typography>
                                <Typography
                                    variant="body2"
                                    color="text.secondary"
                                    sx={{
                                        display: "-webkit-box",
                                        WebkitLineClamp: 2,
                                        WebkitBoxOrient: "vertical",
                                        overflow: "hidden",
                                        textOverflow: "ellipsis",
                                        minHeight: "3em",
                                    }}
                                >
                                    {product.description}
                                </Typography>
                                <Typography variant="subtitle1" color="primary" sx={{ mt: 1 }}>
                                    ₹{product.cost}
                                </Typography>
                            </CardContent>

                            {/* Button */}
                            <CardActions sx={{ justifyContent: "center" }}>
                                <Button
                                    size="small"
                                    variant="contained"
                                    color="primary"
                                    onClick={() => navigate("/order", { state: { product } })}
                                >
                                    Add to Cart
                                </Button>
                            </CardActions>
                        </Card>
                    </Grid>
                ))}
            </Grid>

        </Container>
    );
};
