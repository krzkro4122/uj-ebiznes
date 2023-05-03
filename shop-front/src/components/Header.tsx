interface Props {
    children: React.ReactNode;
}

const Header: React.FC<Props> = ({ children }) => {
    return (
        <div className='shadow-md text-4xl p-3'>
            {children}
        </div>
    );
};

export default Header;