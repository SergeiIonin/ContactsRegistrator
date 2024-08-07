package io.github.sergeiionin.contractsregistrator
package handler

import repository.ContractsRepository

import cats.effect.Resource
import cats.Monad
import domain.Contract

import handler.protos.ProtosHandler

trait ContractsHandler[F[_]]:
  def handle(contract: Contract): F[Unit]

object ContractsHandler:
  def make[F[_] : Monad](repository: ContractsRepository[F],
                         protos: ProtosHandler[F]): Resource[F, ContractsHandler[F]] =
    Resource.pure[F, ContractsHandler[F]](ContractsHandlerImpl[F](repository, protos))