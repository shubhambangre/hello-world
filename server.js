const http = require('http')
const server = http.createServer((req, res) => {
    console.log('New connection')
    res.end('hello-world')
  })
const PORT = process.env.port || 8080
  server.listen (PORT, () => console.log('Listening'))
