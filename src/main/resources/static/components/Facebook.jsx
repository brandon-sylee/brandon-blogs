import React from 'react';
import { FacebookLogin } from 'react-facebook-login-component';

class Login extends React.Component{

    constructor (props, context) {
        super(props, context);
    }

    responseFacebook (response) {
        console.log(response);
    }

    render () {
        return (
            <div>
                <FacebookLogin socialId="183086365478299"
                               language="ko_KR"
                               scope="public_profile,email"
                               responseHandler={this.responseFacebook}
                               xfbml={true}
                               version="v2.8"
                               class="facebook-login"
                               buttonText="Login With Facebook"/>
            </div>
        );
    }

}

export default Login;