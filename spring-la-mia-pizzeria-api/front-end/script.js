fetch('http://localhost:8090/api/1/pizza/all')
.then(response => response.json())
.then(data => console.log(data));