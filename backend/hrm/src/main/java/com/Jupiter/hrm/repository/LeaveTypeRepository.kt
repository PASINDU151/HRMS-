package com.Jupiter.hrm.repository

import com.Jupiter.hrm.config.DbConfig
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException


class LeaveTypeRepository {
    private val connection: Connection
    init {
        try {
            connection = DbConfig.getConnection()
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    fun updateName(id : Long, newName : String) : Boolean{
        try {
            var query : String = "UPDATE leave_type SET name = ? WHERE leave_type_id = ?;"
            val preparedStatement : PreparedStatement = connection.prepareStatement(query)
            preparedStatement.setString(1, newName)
            preparedStatement.setLong(2, id)

            preparedStatement.executeUpdate()
            return true
        }catch (e : SQLException) {
            throw RuntimeException(e);
        }

    }

    fun updateValue(id : Long, value : Double) : Boolean{
        try {
            var query : String = "UPDATE leave_type set pay = ? WHERE leave_type_id = ?"
            val preparedStatement : PreparedStatement = connection.prepareStatement(query)
            preparedStatement.setDouble(1, value)
            preparedStatement.setLong(2, id)
            preparedStatement.executeUpdate()
            return true
        }catch (e : SQLException) {
            throw RuntimeException(e);
        }

    }

}
