import './Entry.css';

const Entry = (props) => {
  const { entry } = props;
  return (
    <div className="Entry">
      {entry.type}
      {entry.checked}
      {entry.dateCreated}
      {entry.content}
    </div>
  );
};

export default Entry;
