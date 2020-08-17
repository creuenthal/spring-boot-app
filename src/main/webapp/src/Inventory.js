import React from 'react';

class Inventory extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            items: []
        };
    }

    componentDidMount() {
        fetch("/inventory")
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        items: result.inventory
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
                    <li key={item.productKey}>
                        {item.productKey}: {item.timeAcquired}
                    </li>
                ))}
            </ul>
        );
    }

    // }
}

export default Inventory;
