import React from 'react';
import ReactDOM from 'react-dom';
import HelloWorld from './static/components/HelloWorld.jsx';
import Login from './static/components/Facebook.jsx'

ReactDOM.render(<Login/>, document.getElementById('login'));

setInterval(function () {
    ReactDOM.render(<HelloWorld/>, document.getElementById('root'));
}, 1000);

