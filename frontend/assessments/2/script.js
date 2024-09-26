
const socket = io("http://localhost:3000");
const price=document.getElementById("price")
fetch('/basedata')
    .then(response => response.json())
    .then(data => {
        const { name, initialPrice } = data;

        const stockNameElement = document.querySelector('.logo p');
        const priceElement = document.querySelector('.price');
        stockNameElement.textContent = name;
        priceElement.textContent = `Price: ${initialPrice}`;
    })
    .catch(error => {
        console.error('Error fetching base data:', error);
    });

    socket.on('update', (priceChange) => {
        let priceElement = document.querySelector('.price');
        let currentPrice = parseInt(priceElement.textContent.split(':')[1].trim());
        
        let newPrice = currentPrice + priceChange;
    
        if (newPrice < 0) {
            newPrice = 0;
        }
    
        let arrow = '';
        let color = '';
        if (priceChange > 0) {
            arrow = '▲'; // Green up arrow unicode
            color = 'green';
        } else if (priceChange < 0) {
            arrow = '▼'; // Red down arrow unicode
            color = 'red';
        }

        let percentageChange = 0;
        if (currentPrice !== 0) {
            percentageChange = ((newPrice - currentPrice) / currentPrice) * 100;
        }
    
        currentPrice = newPrice;
        priceElement.innerHTML = `Price: ${currentPrice} <span style="color: ${color};">${arrow} ${Math.abs(percentageChange).toFixed(2)}%</span>`;
        addBar(priceChange);
    });
    

function addTransactionToHistory(type, quantity, price, time) {
    const historyElement = document.querySelector('.history');
    const transactionBox = document.createElement('div');
    transactionBox.classList.add('transaction-box');
    
    // Add border and margin to the transaction box
    transactionBox.style.border = '1px solid #ccc';
    transactionBox.style.padding = '10px';
    transactionBox.style.marginBottom = '10px';

    const buttonColor = (type === 'buy') ? '#2fe944' : '#e03131';
    
    transactionBox.innerHTML = `
        <div style="display: flex; justify-content: space-between;">
            <div>
                <p>${quantity} Stocks</p>
                <p>Price: Rs.${price}</p>
                <p>${time}</p> <!-- Move the time below the quantity and price -->
            </div>
            <button style="background-color: ${buttonColor};">${type.toUpperCase()}</button>
        </div>
    `;

    historyElement.appendChild(transactionBox);
}


document.querySelector('.buy button').addEventListener('click', () => {
    const quantity = parseInt(document.querySelector('.qty input').value);
    const time = new Date().toLocaleTimeString();
    let priceElement = document.querySelector('.price');
    
    const price = parseInt(priceElement.textContent.split(':')[1].trim());         
    addTransactionToHistory('buy', quantity, price, time);
});

document.querySelector('.sell button').addEventListener('click', () => {
    const quantity = parseInt(document.querySelector('.qty input').value);
    const time = new Date().toLocaleTimeString();
    let priceElement = document.querySelector('.price');
    
    const price = parseInt(priceElement.textContent.split(':')[1].trim()); 
    
    addTransactionToHistory('sell', quantity, price, time);
});
    
function addBar(change) {
    const graphElement = document.querySelector('.graph');
    const barElement = document.createElement('div');
    barElement.classList.add('bar');
    barElement.style.height = Math.abs(change) + 'px'; 

    if (change > 0) {
        barElement.classList.add('positive');
    } else if (change < 0) {
        barElement.classList.add('negative');
    }

    graphElement.appendChild(barElement);

}

