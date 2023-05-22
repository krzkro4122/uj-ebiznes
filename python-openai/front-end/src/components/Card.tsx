import "../styles/Card.css";

interface ICardInfo {
  cardTitle: string;
  link: string;
  key: number;
  index: number;
  className?: string;
  onClick: (index: number) => void;
}

function Card({ cardTitle, link, className, onClick, index }: ICardInfo) {
  return (
    <div className="card">
      <div onClick={() => onClick(index)} className={"cardContainer " + className}>
        <a href={link} className="cardText">
          {cardTitle}
        </a>
      </div>
    </div>
  );
}

export default Card;
