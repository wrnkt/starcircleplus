import React from 'react';
import {
  SafeAreaView,
  View,
  FlatList,
  StyleSheet,
  Text,
  StatusBar,
} from 'react-native';

const DATA = [
  {
    id: 'bd7acbea-c1b1-46c2-aed5-3ad53abb28ba',
    type: 'STAR',
    dateCreated: "2023-03-05T11:37:02.545883-05:00",
    content: 'First Entry',
  },
  {
    id: '3ac68afc-c605-48d3-a4f8-fbd91aa97f63',
    type: 'CIRCLE',
    dateCreated: "2023-03-05T11:37:27.100338-05:00",
    content: 'Second Entry',
  },
  {
    id: '58694a0f-3da1-471f-bd96-145571e29d72',
    type: 'PLUS',
    dateCreated: "2023-03-05T11:37:37.155832-05:00",
    content: 'Third Entry',
  },
];

const EntryTypeDisplay = ({type, checked}) => (
        <View>
            <Text type={styles.entrytypedisplay}>{type}</Text>
        </View>
);

const EntryContentView = ({content}) => (
        <View>
            <Text type={styles.entrycontentview}>{content}</Text>
        </View>
);

const EntryDateView = ({dateCreated}) => (
        <View>
            <Text type={styles.entryDateView}>{dateCreated}</Text>
        </View>
);

const Entry = ({entry}) => (
  <View style={styles.entry}>
    <EntryTypeDisplay type={entry.type}/>
    <EntryContentView content={entry.content}/>
    <EntryDateView dateCreated={entry.dateCreated}/>
  </View>
);

const App = () => {
  return (
    <SafeAreaView style={styles.container}>
      <FlatList
        data={DATA}
        renderItem={({item}) => <Entry entry={item} />}
        keyExtractor={entry => entry.id}
      />
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: StatusBar.currentHeight || 0,
  },
  entry: {
    backgroundColor: '#f9c2ff',
    padding: 20,
    marginVertical: 8,
    marginHorizontal: 16,
  },
  content: {
    fontSize: 32,
  },
});

export default App;
