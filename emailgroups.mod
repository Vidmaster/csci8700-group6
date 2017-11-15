mod EMAILREVIEWS* {
  protecting(NAT) -- predefined type: natural numbers
  protecting(BOOL) -- predefined type: boolean
  protecting(STRING) -- predefined type: strings

  -- Example usage:
  --
  -- Good example:
  -- reduce emailPeerEvaluations(
  --  putStudentC(student("Bob"), putStudentC(student("Dave"), putStudentC(student("Jim"), putStudentC(student("Ted"), newClass)))),
  --  putGroup(putStudent(student("Bob"), putStudent(student("Ted"), emptyGroup)),
  --    putGroup(putStudent(student("Dave"), putStudent(student("Jim"), emptyGroup)), emptyProject))
  -- ) .
  --
  -- Exception example:
  -- reduce emailPeerEvaluations(
  --  putStudentC(student("Bob"), putStudentC(student("Dave"), putStudentC(student("Jim"), putStudentC(student("Ted"), newClass)))),
  --  putGroup(putStudent(student("Bob"), putStudent(student("Ted"), emptyGroup)),
  --    putGroup(putStudent(student("Dave"), emptyGroup), emptyProject))
  -- ) .


  -- sort declarations
  [StudentGroup Student Project Class]

	-- signatures
  op newClass : -> Class
  op enrollStudent : Student Class -> Class
  op putStudentC : Student Class -> Class

  op student : String -> Student

  op emptyProject : -> Project
  op addGroup : StudentGroup Project -> Project
  op putGroup : StudentGroup Project -> Project

	op emptyGroup :  -> StudentGroup
  op group : Student -> StudentGroup
  op groupContains : StudentGroup Student -> Bool
  op addStudent : Student StudentGroup -> StudentGroup
  op putStudent : Student StudentGroup -> StudentGroup
  op groupSize : StudentGroup -> Nat

  op allStudentsInGroups : Class Project -> Bool
  op studentInSomeGroup : Student Project -> Bool

  op emailPeerEvaluations : Class Project -> Bool

  op exDuplicateStudent : -> ?StudentGroup
  op exDuplicateGroup : -> ?Project
  op exDuplicateStudentEnrollment : -> ?Class
  op exNotAllStudentsInGroups : -> ?Bool

	-- axioms
  var C : Class
  var S1 : Student
  var S2 : Student
  var P : Project
  var P1 : Project
  var G : StudentGroup
  var G1 : StudentGroup
  var G2 : StudentGroup

  eq enrollStudent(S1, newClass) = putStudentC(S1, newClass) .
  eq enrollStudent(S1, putStudentC(S1, C)) = exDuplicateStudentEnrollment .
  eq enrollStudent(S1, putStudentC(S2, C)) = enrollStudent(S1, C) .

  eq groupContains(group(S2), S1) = false .
  eq groupContains(group(S1), S1) = true .
  eq groupContains(putStudent(S1, G), S1) = true .
  eq groupContains(putStudent(S2, G), S1) = groupContains(G, S1) .

  eq groupSize(emptyGroup) = 0 .
  eq groupSize(group(S1)) = 1 .
  eq groupSize(putStudent(S1, G)) = 1 + groupSize(G) .

  eq addStudent(S1, emptyGroup) = group(S1) .
  eq addStudent(S1, group(S1)) = exDuplicateStudent .
  eq addStudent(S1, putStudent(S2, G)) = putStudent(S2, addStudent(S1, G)) .
  eq putStudent(S1, emptyGroup) = group(S1) .

  eq addGroup(G1, emptyProject) = putGroup(G1, emptyProject) .
  eq addGroup(G1, putGroup(G1, P)) = exDuplicateGroup .
  eq addGroup(G1, putGroup(G2, P)) = addGroup(G1, P) .

  eq allStudentsInGroups(putStudentC(S1, C), P) = studentInSomeGroup(S1, P) and allStudentsInGroups(C, P) .
  eq allStudentsInGroups(newClass, P) = true .

  eq studentInSomeGroup(S1, emptyProject) = false .
  eq studentInSomeGroup(S1, putGroup(emptyGroup, P)) = studentInSomeGroup(S1, P) .
  eq studentInSomeGroup(S1, putGroup(putStudent(S1, G1), P)) = true .
  eq studentInSomeGroup(S1, putGroup(putStudent(S2, G1), P)) = studentInSomeGroup(S1, putGroup(G1, P)) .

  ceq emailPeerEvaluations(C, P) = true if allStudentsInGroups(C, P) .
  eq emailPeerEvaluations(C, P) = exNotAllStudentsInGroups .

}
