const express = require('express');
const http = require('http');
const socketIo = require('socket.io');

const app = express();
const server = http.createServer(app);
const io = new socketIo.Server(server, {
    cors: {
        origin: "http://localhost:5173",
        methods: ["GET", "POST"]
    }
});

let transactions = [];

const users = ["Jainam", "Sagun", "Aakash", "Nitesh", "Anupam","Rishav","Pratham","Sasi","Varshil"];
let currentUserIndex = 0;

app.use(express.json());

io.on('connection', (socket) => {
  
  const userName = users[currentUserIndex];
  currentUserIndex = (currentUserIndex + 1) % users.length; 
  socket.emit('userName', userName);
  
  console.log(`${userName} user connected`);
  socket.on('joinRoom', (room) => {
    socket.join(room);
  });

  socket.on('leaveRoom', (room) => {
    socket.leave(room);
  });

  socket.on('getInitialTransactions', (room) => {
    const filteredTransactions = transactions.filter(transaction => transaction.stock_name === room);
    socket.emit('initialTransactions', filteredTransactions);
  });

  socket.on('newTransaction', (transaction) => {
    transactions.push(transaction);
    io.to(transaction.stock_name).emit('newTransaction', transaction);
  });

  socket.on('disconnect', () => {
    console.log('User disconnected');
  });
});

const PORT = process.env.PORT || 5000;
server.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});
