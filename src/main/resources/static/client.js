const WebSocket = require('ws');

const socket = new WebSocket('ws://localhost:8080/bishal');

socket.on('open', () => {
    console.log('WebSocket connection is open.');
});

socket.on('message', (data) => {
    console.log('Received:', data);
});

socket.on('error', (error) => {
    console.error('WebSocket error:', error);
});

socket.on('close', (code, reason) => {
    console.log('WebSocket connection closed:', code, reason);
});

setInterval(() => {
    if (socket.readyState === WebSocket.OPEN) {
        const data = Math.random().toPrecision(5).toString();
        socket.send(data);
        console.log('Sent:', data);
    } else {
        console.log('WebSocket connection is not open.');
    }
}, 1000);
