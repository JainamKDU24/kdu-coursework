const express=require('express');
const path=require('path')

let app=express();
app.use(express.json());
app.use('/api/posts',require('./routes/post'));

const PORT = process.env.PORT || 5000;

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});