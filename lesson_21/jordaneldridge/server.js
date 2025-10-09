const express = require('express');
const path = require('path');
const app = express();
const PORT = 3000;

// Serve static files (CSS, images, etc.)
app.use(express.static(__dirname));

// Route for homepage
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'views', 'index.html'));
});

// Route for contact page
app.get('/contact', (req, res) => {
  res.sendFile(path.join(__dirname, 'views', 'contact.html'));
});

app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:3000`);
});