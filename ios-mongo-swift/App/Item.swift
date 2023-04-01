import RealmSwift

class Item: Object, ObjectKeyIdentifiable {
    @Persisted(primaryKey: true) var _id: ObjectId
    @Persisted var isComplete = false
    @Persisted var textContent: String
    @Persisted var type: EntryType
    @Persisted var owner_id: String
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
