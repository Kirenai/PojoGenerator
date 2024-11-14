import { HStack } from '@chakra-ui/react'
import PojoForm from './components/PojoForm'

const PojoHome = () => {
  return (
    <HStack p="4" justifyContent="center">
      <PojoForm />
    </HStack>
  )
}

export default PojoHome
