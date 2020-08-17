import React from 'react';

class Catalog extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            items: []
        };
    }

    componentDidMount() {
        fetch("/catalog")
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        items: result.products
                    });
                },
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }

    render() {
        return (
            <ul>
                {this.state.items.map(item => (
                    <li key={item.id}>
                        {item.id}: {item.name}
                    </li>
                ))}
            </ul>
        );
    }

    // }
}

export default Catalog;
