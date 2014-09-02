/**
 * Generated by Scrooge
 *   version: 3.16.3
 *   rev: b8593c83072d94fc44feaa8d97940b9266d84ed0
 *   built at: 20140806-054445
 */
package com.evidence.techops.cass

import com.twitter.finagle.{SourcedException, Service => FinagleService}
import com.twitter.finagle.stats.{NullStatsReceiver, StatsReceiver}
import com.twitter.finagle.thrift.ThriftClientRequest
import com.twitter.scrooge.{ThriftStruct, ThriftStructCodec}
import com.twitter.util.{Future, Return, Throw}
import java.nio.ByteBuffer
import java.util.Arrays
import org.apache.thrift.protocol._
import org.apache.thrift.TApplicationException
import org.apache.thrift.transport.{TMemoryBuffer, TMemoryInputTransport}
import scala.collection.{Map, Set}


@javax.annotation.Generated(value = Array("com.twitter.scrooge.Compiler"))
class CassOpsAgent$FinagleClient(
  val service: FinagleService[ThriftClientRequest, Array[Byte]],
  val protocolFactory: TProtocolFactory = new TBinaryProtocol.Factory,
  val serviceName: String = "",
  stats: StatsReceiver = NullStatsReceiver
) extends CassOpsAgent[Future] {
  import CassOpsAgent._

  protected def encodeRequest(name: String, args: ThriftStruct) = {
    val buf = new TMemoryBuffer(512)
    val oprot = protocolFactory.getProtocol(buf)

    oprot.writeMessageBegin(new TMessage(name, TMessageType.CALL, 0))
    args.write(oprot)
    oprot.writeMessageEnd()

    val bytes = Arrays.copyOfRange(buf.getArray, 0, buf.length)
    new ThriftClientRequest(bytes, false)
  }

  protected def decodeResponse[T <: ThriftStruct](resBytes: Array[Byte], codec: ThriftStructCodec[T]) = {
    val iprot = protocolFactory.getProtocol(new TMemoryInputTransport(resBytes))
    val msg = iprot.readMessageBegin()
    try {
      if (msg.`type` == TMessageType.EXCEPTION) {
        val exception = TApplicationException.read(iprot) match {
          case sourced: SourcedException =>
            if (serviceName != "") sourced.serviceName = serviceName
            sourced
          case e => e
        }
        throw exception
      } else {
        codec.decode(iprot)
      }
    } finally {
      iprot.readMessageEnd()
    }
  }

  protected def missingResult(name: String) = {
    new TApplicationException(
      TApplicationException.MISSING_RESULT,
      name + " failed: unknown result"
    )
  }

  protected def setServiceName(ex: Exception): Exception =
    if (this.serviceName == "") ex
    else {
      ex match {
        case se: SourcedException =>
          se.serviceName = this.serviceName
          se
        case _ => ex
      }
    }

  // ----- end boilerplate.

  private[this] val scopedStats = if (serviceName != "") stats.scope(serviceName) else stats
  private[this] object __stats_getStatus {
    val RequestsCounter = scopedStats.scope("getStatus").counter("requests")
    val SuccessCounter = scopedStats.scope("getStatus").counter("success")
    val FailuresCounter = scopedStats.scope("getStatus").counter("failures")
    val FailuresScope = scopedStats.scope("getStatus").scope("failures")
  }
  
  def getStatus(): Future[String] = {
    __stats_getStatus.RequestsCounter.incr()
    this.service(encodeRequest("getStatus", getStatus$args())) flatMap { response =>
      val result = decodeResponse(response, getStatus$result)
      val exception: Future[Nothing] =
        null
  
      if (result.success.isDefined)
        Future.value(result.success.get)
      else if (exception != null)
        exception
      else
        Future.exception(missingResult("getStatus"))
    } respond {
      case Return(_) =>
        __stats_getStatus.SuccessCounter.incr()
      case Throw(ex) =>
        __stats_getStatus.FailuresCounter.incr()
        __stats_getStatus.FailuresScope.counter(ex.getClass.getName).incr()
    }
  }
  private[this] object __stats_getColumnFamilyMetric {
    val RequestsCounter = scopedStats.scope("getColumnFamilyMetric").counter("requests")
    val SuccessCounter = scopedStats.scope("getColumnFamilyMetric").counter("success")
    val FailuresCounter = scopedStats.scope("getColumnFamilyMetric").counter("failures")
    val FailuresScope = scopedStats.scope("getColumnFamilyMetric").scope("failures")
  }
  
  def getColumnFamilyMetric(keySpace: String, colFamily: String): Future[String] = {
    __stats_getColumnFamilyMetric.RequestsCounter.incr()
    this.service(encodeRequest("getColumnFamilyMetric", getColumnFamilyMetric$args(keySpace, colFamily))) flatMap { response =>
      val result = decodeResponse(response, getColumnFamilyMetric$result)
      val exception: Future[Nothing] =
        null
  
      if (result.success.isDefined)
        Future.value(result.success.get)
      else if (exception != null)
        exception
      else
        Future.exception(missingResult("getColumnFamilyMetric"))
    } respond {
      case Return(_) =>
        __stats_getColumnFamilyMetric.SuccessCounter.incr()
      case Throw(ex) =>
        __stats_getColumnFamilyMetric.FailuresCounter.incr()
        __stats_getColumnFamilyMetric.FailuresScope.counter(ex.getClass.getName).incr()
    }
  }
  private[this] object __stats_incrementalBackup {
    val RequestsCounter = scopedStats.scope("incrementalBackup").counter("requests")
    val SuccessCounter = scopedStats.scope("incrementalBackup").counter("success")
    val FailuresCounter = scopedStats.scope("incrementalBackup").counter("failures")
    val FailuresScope = scopedStats.scope("incrementalBackup").scope("failures")
  }
  
  def incrementalBackup(keySpace: String): Future[String] = {
    __stats_incrementalBackup.RequestsCounter.incr()
    this.service(encodeRequest("incrementalBackup", incrementalBackup$args(keySpace))) flatMap { response =>
      val result = decodeResponse(response, incrementalBackup$result)
      val exception: Future[Nothing] =
        if (false)
          null // can never happen, but needed to open a block
        else if (result.ea.isDefined)
          Future.exception(setServiceName(result.ea.get))
        else
          null
  
      if (result.success.isDefined)
        Future.value(result.success.get)
      else if (exception != null)
        exception
      else
        Future.exception(missingResult("incrementalBackup"))
    } respond {
      case Return(_) =>
        __stats_incrementalBackup.SuccessCounter.incr()
      case Throw(ex) =>
        __stats_incrementalBackup.FailuresCounter.incr()
        __stats_incrementalBackup.FailuresScope.counter(ex.getClass.getName).incr()
    }
  }
  private[this] object __stats_snapshotBackup {
    val RequestsCounter = scopedStats.scope("snapshotBackup").counter("requests")
    val SuccessCounter = scopedStats.scope("snapshotBackup").counter("success")
    val FailuresCounter = scopedStats.scope("snapshotBackup").counter("failures")
    val FailuresScope = scopedStats.scope("snapshotBackup").scope("failures")
  }
  
  def snapshotBackup(keySpace: String): Future[String] = {
    __stats_snapshotBackup.RequestsCounter.incr()
    this.service(encodeRequest("snapshotBackup", snapshotBackup$args(keySpace))) flatMap { response =>
      val result = decodeResponse(response, snapshotBackup$result)
      val exception: Future[Nothing] =
        if (false)
          null // can never happen, but needed to open a block
        else if (result.ea.isDefined)
          Future.exception(setServiceName(result.ea.get))
        else
          null
  
      if (result.success.isDefined)
        Future.value(result.success.get)
      else if (exception != null)
        exception
      else
        Future.exception(missingResult("snapshotBackup"))
    } respond {
      case Return(_) =>
        __stats_snapshotBackup.SuccessCounter.incr()
      case Throw(ex) =>
        __stats_snapshotBackup.FailuresCounter.incr()
        __stats_snapshotBackup.FailuresScope.counter(ex.getClass.getName).incr()
    }
  }
  private[this] object __stats_snapshotBackup2 {
    val RequestsCounter = scopedStats.scope("snapshotBackup2").counter("requests")
    val SuccessCounter = scopedStats.scope("snapshotBackup2").counter("success")
    val FailuresCounter = scopedStats.scope("snapshotBackup2").counter("failures")
    val FailuresScope = scopedStats.scope("snapshotBackup2").scope("failures")
  }
  
  def snapshotBackup2(keySpace: String): Future[String] = {
    __stats_snapshotBackup2.RequestsCounter.incr()
    this.service(encodeRequest("snapshotBackup2", snapshotBackup2$args(keySpace))) flatMap { response =>
      val result = decodeResponse(response, snapshotBackup2$result)
      val exception: Future[Nothing] =
        if (false)
          null // can never happen, but needed to open a block
        else if (result.ea.isDefined)
          Future.exception(setServiceName(result.ea.get))
        else
          null
  
      if (result.success.isDefined)
        Future.value(result.success.get)
      else if (exception != null)
        exception
      else
        Future.exception(missingResult("snapshotBackup2"))
    } respond {
      case Return(_) =>
        __stats_snapshotBackup2.SuccessCounter.incr()
      case Throw(ex) =>
        __stats_snapshotBackup2.FailuresCounter.incr()
        __stats_snapshotBackup2.FailuresScope.counter(ex.getClass.getName).incr()
    }
  }
  private[this] object __stats_commitLogBackup {
    val RequestsCounter = scopedStats.scope("commitLogBackup").counter("requests")
    val SuccessCounter = scopedStats.scope("commitLogBackup").counter("success")
    val FailuresCounter = scopedStats.scope("commitLogBackup").counter("failures")
    val FailuresScope = scopedStats.scope("commitLogBackup").scope("failures")
  }
  
  def commitLogBackup(): Future[String] = {
    __stats_commitLogBackup.RequestsCounter.incr()
    this.service(encodeRequest("commitLogBackup", commitLogBackup$args())) flatMap { response =>
      val result = decodeResponse(response, commitLogBackup$result)
      val exception: Future[Nothing] =
        if (false)
          null // can never happen, but needed to open a block
        else if (result.ea.isDefined)
          Future.exception(setServiceName(result.ea.get))
        else
          null
  
      if (result.success.isDefined)
        Future.value(result.success.get)
      else if (exception != null)
        exception
      else
        Future.exception(missingResult("commitLogBackup"))
    } respond {
      case Return(_) =>
        __stats_commitLogBackup.SuccessCounter.incr()
      case Throw(ex) =>
        __stats_commitLogBackup.FailuresCounter.incr()
        __stats_commitLogBackup.FailuresScope.counter(ex.getClass.getName).incr()
    }
  }
  private[this] object __stats_restoreBackup {
    val RequestsCounter = scopedStats.scope("restoreBackup").counter("requests")
    val SuccessCounter = scopedStats.scope("restoreBackup").counter("success")
    val FailuresCounter = scopedStats.scope("restoreBackup").counter("failures")
    val FailuresScope = scopedStats.scope("restoreBackup").scope("failures")
  }
  
  def restoreBackup(keySpace: String, snapShotName: String, hostId: String): Future[Unit] = {
    __stats_restoreBackup.RequestsCounter.incr()
    this.service(encodeRequest("restoreBackup", restoreBackup$args(keySpace, snapShotName, hostId))) flatMap { response =>
      val result = decodeResponse(response, restoreBackup$result)
      val exception: Future[Nothing] =
        if (false)
          null // can never happen, but needed to open a block
        else if (result.ea.isDefined)
          Future.exception(setServiceName(result.ea.get))
        else
          null
  
      if (exception != null) exception else Future.Done
    } respond {
      case Return(_) =>
        __stats_restoreBackup.SuccessCounter.incr()
      case Throw(ex) =>
        __stats_restoreBackup.FailuresCounter.incr()
        __stats_restoreBackup.FailuresScope.counter(ex.getClass.getName).incr()
    }
  }
  private[this] object __stats_csvToSsTableConv {
    val RequestsCounter = scopedStats.scope("csvToSsTableConv").counter("requests")
    val SuccessCounter = scopedStats.scope("csvToSsTableConv").counter("success")
    val FailuresCounter = scopedStats.scope("csvToSsTableConv").counter("failures")
    val FailuresScope = scopedStats.scope("csvToSsTableConv").scope("failures")
  }
  
  def csvToSsTableConv(csvFilePath: String, keySpace: String, colFamily: String, partitioner: String): Future[String] = {
    __stats_csvToSsTableConv.RequestsCounter.incr()
    this.service(encodeRequest("csvToSsTableConv", csvToSsTableConv$args(csvFilePath, keySpace, colFamily, partitioner))) flatMap { response =>
      val result = decodeResponse(response, csvToSsTableConv$result)
      val exception: Future[Nothing] =
        if (false)
          null // can never happen, but needed to open a block
        else if (result.ea.isDefined)
          Future.exception(setServiceName(result.ea.get))
        else
          null
  
      if (result.success.isDefined)
        Future.value(result.success.get)
      else if (exception != null)
        exception
      else
        Future.exception(missingResult("csvToSsTableConv"))
    } respond {
      case Return(_) =>
        __stats_csvToSsTableConv.SuccessCounter.incr()
      case Throw(ex) =>
        __stats_csvToSsTableConv.FailuresCounter.incr()
        __stats_csvToSsTableConv.FailuresScope.counter(ex.getClass.getName).incr()
    }
  }
  private[this] object __stats_ssTableImport {
    val RequestsCounter = scopedStats.scope("ssTableImport").counter("requests")
    val SuccessCounter = scopedStats.scope("ssTableImport").counter("success")
    val FailuresCounter = scopedStats.scope("ssTableImport").counter("failures")
    val FailuresScope = scopedStats.scope("ssTableImport").scope("failures")
  }
  
  def ssTableImport(ssTableFilePath: String, keySpace: String, colFamily: String): Future[Boolean] = {
    __stats_ssTableImport.RequestsCounter.incr()
    this.service(encodeRequest("ssTableImport", ssTableImport$args(ssTableFilePath, keySpace, colFamily))) flatMap { response =>
      val result = decodeResponse(response, ssTableImport$result)
      val exception: Future[Nothing] =
        if (false)
          null // can never happen, but needed to open a block
        else if (result.ea.isDefined)
          Future.exception(setServiceName(result.ea.get))
        else
          null
  
      if (result.success.isDefined)
        Future.value(result.success.get)
      else if (exception != null)
        exception
      else
        Future.exception(missingResult("ssTableImport"))
    } respond {
      case Return(_) =>
        __stats_ssTableImport.SuccessCounter.incr()
      case Throw(ex) =>
        __stats_ssTableImport.FailuresCounter.incr()
        __stats_ssTableImport.FailuresScope.counter(ex.getClass.getName).incr()
    }
  }
}