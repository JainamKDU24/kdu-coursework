import { createAsyncThunk } from "@reduxjs/toolkit";
import { Product } from "./Content";

export const getProducts = createAsyncThunk(
    "getProducts",
    async () => {
        try{
            const res = await fetch('https://fakestoreapi.com/products');
            const data = await res.json();
            const categories = ['All', ...new Set(data.map((product:Product) => product.category))];
            console.log(categories);
            return {data,categories};
        }
        catch{
            throw new Error("fetching data failed");
        }
    }
)

