import React from 'react';

class Post extends React.Component {
    render(){
        return (
            <div>
                <ul>
                    <li>title</li>
                    <li>created</li>
                </ul>
                <div>
                    contents
                </div>
            </div>
        );
    }
}

export default Post;