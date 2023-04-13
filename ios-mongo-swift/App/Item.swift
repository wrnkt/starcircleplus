import RealmSwift
import UIKit

class Item: Object, ObjectKeyIdentifiable {
    @Persisted(primaryKey: true) var _id: ObjectId
    @Persisted var owner_id: String
    @Persisted var type: EntryType
    @Persisted var isComplete = false
    @Persisted var textContent: String
    @Persisted var tags: List<Tag>
    
    static var previewRealm: Realm {
        var realm: Realm
        let identifier = "previewRealm"
        let config = Realm.Configuration(inMemoryIdentifier: identifier)
        do {
            realm = try Realm(configuration: config)
            // Check to see whether the in-memory realm already contains an Item.
            // If it does, we'll just return the existing realm.
            // If it doesn't, we'll add an Item and append the Tags.
            let realmObjects = realm.objects(Item.self)
            if realmObjects.count != 0 {
                return realm
            } else {
                try realm.write {
                    realm.add(Item.testNote)
                    realm.add(Item.testTodo)
                    realm.add(Item.testSpecial)
                }
                return realm
            }
        } catch let error {
            fatalError("Can't bootstrap item data: \(error.localizedDescription)")
        }
    }

 
}

extension Item {
    static let testNote = Item(value: ["type": EntryType.note, "isComplete": false, "textContent": "This is a note Entry."])
    static let testTodo = Item(value: ["type": EntryType.todo, "isComplete": false, "textContent": "This is a todo Entry."])
    static let testSpecial = Item(value: ["type": EntryType.special, "isComplete": false, "textContent": "This is a special Entry."])
}

enum EntryType: Int, PersistableEnum, CaseIterable {
    case todo = 0
    case special = 1
    case note = 2
    
    
    var description: String {
        switch self {
        case .todo: return "Circle"
        case .special: return "Star"
        case .note: return "Plus"
        }
    }
}

class Tag: Object, ObjectKeyIdentifiable {
    @Persisted(primaryKey: true) var _id: ObjectId
    @Persisted var owner_id: String
    @Persisted var name: String
    @Persisted(originProperty: "tags") var item: LinkingObjects<Item>
}

