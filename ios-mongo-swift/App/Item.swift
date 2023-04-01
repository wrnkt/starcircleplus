import RealmSwift

class Item: Object, ObjectKeyIdentifiable {
    @Persisted(primaryKey: true) var _id: ObjectId
    @Persisted var isComplete = false
    @Persisted var textContent: String
    @Persisted var priority: PriorityLevel
    @Persisted var owner_id: String
}

enum PriorityLevel: Int, PersistableEnum, CaseIterable {
    case severe = 0
    case high = 1
    case medium = 2
    case low = 3
    
    var description: String {
        switch self {
        case .severe: return "Severe"
        case .high: return "High"
        case .medium: return "Medium"
        case .low: return "Low"
        }
    }
}
